package com.example.openAPI.controller;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.XMLFormatter;

//공공데이터 인증키
//HIVZzhQpW%2BXuIwc8TdHtjjITL8zkdjbLHMe80yY8Ub21YCfA%2BvEK2RUqTXp7OycPYmwRrFEsQisnLuJrSY24JQ%3D%3D
//요청 url
//http://apis.data.go.kr/6260000/FoodService/getFoodKr



//공공데이터를 가져올 때 데이터를 주고받는 내용이 있기 때문에 RestController

@RestController
public class RestTestController {

    //서버를 실행하면 웹브라우저에서 url작성
    //디스펙쳐서블릿이 모든 url을 받는다
    //전송할때 GET,POST 인지 구별해서 맵핑을 시켜준다

    @GetMapping("/apitest/{pageNo}")
    public String openApi(@PathVariable int pageNo){

        StringBuffer result = new StringBuffer();

        int pageNumber = pageNo;

        try {
            //필수 요소와 url을 저장하는 내용
            //요청 URL
            String apiUrl = "http://apis.data.go.kr/6260000/FoodService/getFoodKr?" +
                //요청을 하는 필수요소 넣어주기
                //인증키,한 페이지 결과 수,페이지 번호
                "serviceKey=HIVZzhQpW%2BXuIwc8TdHtjjITL8zkdjbLHMe80yY8Ub21YCfA%2BvEK2RUqTXp7OycPYmwRrFEsQisnLuJrSY24JQ%3D%3D" +
                "&numOfRows=10"+
                "&pageNo="+ pageNumber + "&resultType=xml";

            //URL 객체를 생성하는 클래스 //문자열이 지정하는 자원에 대한 url 객체 생성
            URL url = new URL(apiUrl);

            //url 내용을 읽어오거나 url에 get,post 방식으로 데이터를 전달할때 사용
            //웹페이지나 서블에 데이터를 전달 할 수 있다
            //프로토콜이 http://인 경우 반환된 객체를 HttpURLConnerction객체로 캐스팅 할 수 있다
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); //연결

            //실제 데이터 전송을 할 때 전송방식
            urlConnection.setRequestMethod("GET");

            //실제 연결
            //urlConnection.connect();

            //응답(데이터를 받아온다)
            //Input , Out Stream
            //Stream
            //-데이터가 연속적으로 존재한다는 것을 표현한 객체
            //-바이트로 데이터를 전달하기 때문에 스트림도 byte 연속된 집합
            //-사용자의 키보드의 입력, 파일데이터, http 송수신 데이터등이 모두 스트림이라고 할 수 있다

            //api도 스트림으로 처리한다

           //InputStream (자바 프로그램 안으로 데이터를 가지고 온다)
           //1byte의 int형 아스키코드값으로 값을 저장한다 (1byte씩 전송되서 느리다 그래서 버퍼를 쓴다)

           //InputStreamReader
            // byte 대신 char 형태로 읽을 수 있게 아스키코드가 아닌 문자열로 출력이 가능하다
            //String 객체로 변환할 수도 있게 된다
            // InputStream 인자로 받아와서 만들어진다

            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

            //실제 응답받은 데이터 읽기
            //한꺼번에 읽기 위해 버퍼
            //깨질수도 있어서 인코딩도 해준다
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream,"UTF-8"));

            String returnLine;
            
            //문자, 숫자, 태그소스든 그래도 출력할 수 있게 도와주는 태그
            result.append("<xmp>");

            //데이터를 응답받아서 안에 있는 내용들을 꺼내야된다
            //꺼낼때 데이터가 없으면 null 반환한다
            //한 행 , 한 줄을 읽어올 수 있는 BufferedReader에 기능이있다 readLine()
            while ((returnLine = bufferedReader.readLine()) != null){
                result.append(returnLine + "\n"); //줄바꿈이 안되서 넘어오기때문에 수동으로 \n
            }

            //url 연결끊기
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "</xmp>";
    }

    //json타입은 key,value
    //필요한 라이브러리가 있다

    @GetMapping("/josnapi/{pageNo}")
    public String openapiJson(@PathVariable int pageNo) {

        // url 세팅
        StringBuffer result = new StringBuffer();
        int pageNumber = pageNo;
        // try안쪽에 변수선언하면 데이터가 try문과 함꼐 사라지는 지역변수!
        String jsonPrintString = null;

        try {
            String apiUrl = "http://apis.data.go.kr/6260000/FoodService/getFoodKr?"
                    + "serviceKey=aCz34R3ycz%2B0IcuBdueR1Qzo7wICjHTJowOpM9iFLFXvD5K718SqKMB34EP9zkf%2ByDq0pKCI1L5FIaI8Mzf78A%3D%3D"
                    + "&numOfRows=10" + "&pageNo=" + pageNumber
                    ;

            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // 연결
            urlConnection.connect();

            BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(urlConnection.getInputStream());

            // 인코딩 같이함
            // 중간에 데이터를 임시저장공간인 버퍼에 저장한다.
            // 저장한 내용을 한꺼번에 가지고 들어온다.
            // 1byte 가져오면 속도가 느리고 데이터의 용량이 크면 시간이 꾀 오래걸린다.

            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(bufferedInputStream, "UTF-8")
                    );

            String returnLine;

            while ((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            // json
            // json 파일을 스프링부트에서 사용할 수 있도록 특정값을 가지고 오는
            // 내용을 작성!

            // Jsonparser 객체의 도움을 받는다.
            // 1. Jsonparser 객체 생성
            // 2. reader 를 이용해서 json 파일을 읽어온다.
            // 3. Array  json코드가 [] 감싸고 있을 경우 List형식으로 index값으로
            //   불러온다.

            // 4. Object json 코드가 {}로 감싸고 있는 경우
            //     Key : Value 형식으로 저장되어있는 값을 불러온다. map형식

            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonPrintString;
    }
}
