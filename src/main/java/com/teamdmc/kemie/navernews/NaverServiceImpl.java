package com.teamdmc.kemie.navernews;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service("naverService")
public class NaverServiceImpl implements NaverService {
	
	final static Logger LOG = LogManager.getLogger(NaverSearch.class);
	
	public NaverServiceImpl() {}

	@Override
	public List<Item> doRetrieve() throws SQLException {
		
		String clientId = "qtzKbfAmebbuZA7YM4FC"; //회원 ID
		String clienSecret = "lZWp_wbt7J";        //회원비번
		LOG.debug("=================================");
		Channel channel = null;
		try {
			String searchText = URLEncoder.encode("코인", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/news?display=5&query="+searchText;//json
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+searchText;//xml
			
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clienSecret);
			
			int responseCode = con.getResponseCode();
			LOG.debug("responseCode : "+responseCode);
			
			BufferedReader br;
			
			if(200 == responseCode) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine = "";
			StringBuffer responseData = new StringBuffer(2000);
			
			while((inputLine = br.readLine()) != null) {
				//LOG.debug(inputLine);
				responseData.append(inputLine);
			}
			
			br.close();
			
			Gson gson = new Gson();
			channel = gson.fromJson(responseData.toString(), Channel.class);
			
			for(Item item :channel.getItems()) {
				LOG.debug(item);
				String strDate = item.getPubDate();
				LOG.debug("strDate: "+strDate);
				SimpleDateFormat dtFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
				SimpleDateFormat pbFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				
				Date newStrDate = dtFormat.parse(strDate.trim());
				LOG.debug("newStrDate: "+newStrDate.toString());
				String newPubDate = pbFormat.format(newStrDate);
				LOG.debug("newPubDate: "+newPubDate);
				item.setPubDate(newPubDate);
			}
			
		}catch(Exception e) {
			LOG.debug("=================================");
			LOG.debug("e : "+ e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}
		return channel.getItems();
	}

}
