import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("key.txt"));
        String key = sc.next(); //API 인증 키
        StringBuilder urlBuilder = new StringBuilder("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON");
        urlBuilder.append("?authkey=").append(key); //인증키(필수)
        //urlBuilder.append("&searchdate=").append("2022-09-27"); //default : 현재일
        urlBuilder.append("&data=").append("AP01"); //데이터 종류(필수), AP01밖에 안됨(환율)

        try {
            URL url = new URL(urlBuilder.toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String result = br.readLine();
            System.out.println(result); //JSON을 그대로 출력

            //JSON 파싱
            JSONParser parser = new JSONParser();
            JSONArray arr = (JSONArray) parser.parse(result); //처음부터 배열로 묶여있음
            for(int i = 0; i < arr.size(); i++) {
                JSONObject obj = (JSONObject) arr.get(i); //배열의 i번째 요소(국가별로 저장)
                System.out.println("통화코드 : " + obj.get("cur_unit"));
                System.out.println("국가/통화명 : " + obj.get("cur_nm"));
                System.out.println("전산환(송금) 받으실때 : " + obj.get("ttb"));
                System.out.println("전산환(송금) 보내실때 : " + obj.get("tts"));
                System.out.println("매매 기준율 : " + obj.get("deal_bas_r"));
                System.out.println("장부가격 : " + obj.get("bkpr"));
                System.out.println("년환가료율 : " + obj.get("yy_efee_r"));
                System.out.println("10일환가로율 : " + obj.get("ten_dd_efee_r"));
                System.out.println("서울외국환중개 매매기준율 : " + obj.get("kftc_deal_bas_r"));
                System.out.println("서울외국환중개 장부가격 : " + obj.get("kftc_bkpr"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}