package sirketdi.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>>  GetJsonDataToMap() throws IOException {
		
		//Json dosyasını Stringe çevireceğim
		
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//sirketdi//Data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		//stinge çevrilen json dosyasını, hashMap e çeviriyorum.
		
		ObjectMapper mapper= new ObjectMapper();
		
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		
		return data;
	}

}
