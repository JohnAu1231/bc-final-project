package com.bootcamp.bc_stock_web.infra;

public class COPY {
  
}

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URI;
// import java.net.URL;
// // import org.slf4j.Logger;
// // import org.slf4j.LoggerFactory;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// public class CrumbManager {
//   // private final static Logger log = LoggerFactory.getLogger(CrumbManager.class);

//   private static String crumb = null;
//   private static String cookie = null;


//   private static void setCookie() {
//     try {
//       URL url = new URI("http://fc.yahoo.com").toURL();
//       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//       //Get the cookie from the response headers
//       cookie = connection.getHeaderField("Set-Cookie");
//       connection.disconnect();;
//     } catch (Exception e) {
//       log.debug("Failed to set cookie from http request. Intraday quote requests will most likely fail.", e);
//     }
//   }

//   public static void setCrumb(){
//     log.info("---------startSetCrumb----------------");
//     StringBuilder response = new StringBuilder();
//     String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 Edg/185.225.234.18";

//     try {
//       log.info("-----------first-----try-------");
//       URL url = new URI("https://query2.finance.yahoo.com/v1/test/getcrumb").toURL();
//       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//       //Set the cookie
//       connection.setRequestProperty("Cookie", cookie);
//       connection.addRequestProperty("User-Agent", userAgent);

//       //Make the HTTP request
//       connection.setRequestMethod("GET");

//       //Read the response content
//       try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()
//       ))) {
      
//         String line;
//         while ((line = reader.readLine()) != null) {
//           response.append(line);
//           log.info("---------------line : " + line);
//         }
//       }
//     } catch (Exception e) {
//       log.debug("Failed to set crumb from http request. Intraday quote request will most likely fail.", e);
//     }
//     crumb = response.toString();
//     log.info("--------endSetCrumb----------------");
//   }

//   public static synchronized void resetCookieCrumb() {
//     log.info("---------callsetCookie----------------");
//     setCookie();
//     log.info("---------callsetCrumb----------------");
//     setCrumb();
//   }

//   public static synchronized String getCookie() {
//     if (cookie == null || cookie.isEmpty()) {
//       resetCookieCrumb();
//     }
//     return cookie;
//   }

//   public static synchronized String getCrumb() {
//     if (crumb == null || crumb.isBlank()) {
//       resetCookieCrumb();
//     }
//     return crumb;
//   }
// }

