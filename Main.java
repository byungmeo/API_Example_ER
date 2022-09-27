import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    enum Data {
        AP01, //환율
        AP02, //대출금리
        AP03 //국제금리
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("key.txt"));
        String key = sc.next(); //API 인증 키
        System.out.println(key);
        StringBuilder urlBuilder = new StringBuilder("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON");
        urlBuilder.append("?authkey=").append(key); //인증키(필수)
        //urlBuilder.append("&searchdate=").append("2022-09-27"); //default : 현재일
        urlBuilder.append("&data=").append(Data.AP01); //데이터 종류(필수)

        try {
            URL url = new URL(urlBuilder.toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String result = br.readLine();
            System.out.println(result); //JSON을 그대로 출력

            //JSON 파싱
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}