package com.teamdmc.kemie.naver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Repository("naverDao")
public class NaverDaoImpl implements NaverDao {

final Logger LOG = LogManager.getLogger(this.getClass());
	
	public NaverDaoImpl() {}

	public NaverSearchVO doRetrieve() throws SQLException {
		
		String clientId = "qtzKbfAmebbuZA7YM4FC"; //회원 ID
		String clienSecret = "lZWp_wbt7J";        //회원비번
		LOG.debug("=================================");
		NaverSearchVO outVO = null;
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
			LOG.debug("=====responseData"+responseData);
			br.close();
			
			outVO = new Gson().fromJson(responseData.toString(), NaverSearchVO.class);
			
			SimpleDateFormat dtFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			SimpleDateFormat pbFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
			Date newStrDate = null;

			for(NaverSearchItemVO item : outVO.getItems()) {
				newStrDate = dtFormat.parse(item.getPubDate().trim());
				LOG.debug("newStrDate: "+newStrDate.toString());
				
				item.setPubDate(pbFormat.format(newStrDate));
				LOG.debug("pubDate: "+item.getPubDate());
			}
		}catch(Exception e) {
			LOG.debug("=================================");
			LOG.debug("e : "+ e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}
		return outVO;
	}
	
}
