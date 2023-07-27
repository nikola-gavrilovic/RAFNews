package rs.raf.demo.filters;

import javax.servlet.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

//import java.io.IOException;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.ext.Provider;
//
////@Provider
//public class CORSFilter implements ContainerResponseFilter {
//
//   @Override
//   public void filter(ContainerRequestContext requestContext,
//                      ContainerResponseContext responseContext) throws IOException {
//      responseContext.getHeaders().add(
//              "Access-Control-Allow-Origin", "*");
//      responseContext.getHeaders().add(
//              "Access-Control-Allow-Credentials", "true");
//              responseContext.getHeaders().add(
//                      "Access-Control-Allow-Headers",
//                      "origin, content-type, accept, authorization");
//      responseContext.getHeaders().add(
//              "Access-Control-Allow-Methods",
//              "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//   }
//}
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class CORSFilter implements Filter {
//   @Override
//   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//           throws IOException, ServletException {
//      HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//      // Setovanje odgovarajućih CORS headera
//      httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//      httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//      httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
//
//      chain.doFilter(request, response);
//   }
//
//   // Implementacija preostalih metoda interfejsa Filter
//}
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//
//public class RestCorsFilter implements Filter {
//
//   @Override
//   public void destroy() {
//
//   }
//
//   @Override
//   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//           throws IOException, ServletException {
//
//      HttpServletResponse resp = (HttpServletResponse) response;
//      resp.addHeader("Access-Control-Allow-Origin", "");
//      resp.addHeader("Access-Control-Allow-Headers", "");
//      resp.addHeader("Access-Control-Allow-Methods", "*");
//
//      chain.doFilter(request, resp);
//
//   }
//
//   @Override
//   public void init(FilterConfig arg0) throws ServletException {
//
//   }
//
//}
//@Provider
//public class CorsFilter implements ContainerResponseFilter {
//
//   @Override
//   public void filter(ContainerRequestContext requestContext,
//                      ContainerResponseContext responseContext) throws IOException {
//
//      System.out.println("USAO U CORSSSSSSSSSSSSSSSSSSS");
//
//      if(requestContext.getUriInfo().getPath().contains("login") || requestContext.getRequest().getMethod().equals("OPTIONS")){
//         return;
//      }
//
//      responseContext.getHeaders().add(
//              "Access-Control-Allow-Origin", "*");
//      responseContext.getHeaders().add(
//              "Access-Control-Allow-Credentials", "true");
//      responseContext.getHeaders().add(
//              "Access-Control-Allow-Headers",
//              "origin, content-type, accept, authorization");
//      responseContext.getHeaders().add(
//              "Access-Control-Allow-Methods",
//              "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//   }
//}

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CORSFilter
 */
// Enable it for Servlet 3.x implementations
/* @ WebFilter(asyncSupported = true, urlPatterns = { "/*" }) */
public class CorsFilter implements Filter {

   /**
    * Default constructor.
    */
   public CorsFilter() {
      // TODO Auto-generated constructor stub
   }

   /**
    * @see Filter#destroy()
    */
   public void destroy() {
      // TODO Auto-generated method stub
   }

   /**
    * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
    */
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
           throws IOException, ServletException {

      HttpServletRequest request = (HttpServletRequest) servletRequest;
      System.out.println("CORSFilter HTTP Request: " + request.getMethod());

      // Authorize (allow) all domains to consume the content
      ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
      ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");

      HttpServletResponse resp = (HttpServletResponse) servletResponse;

      // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
      if (request.getMethod().equals("OPTIONS")) {
         resp.setStatus(HttpServletResponse.SC_ACCEPTED);
         return;
      }

      // pass the request along the filter chain
      chain.doFilter(request, servletResponse);
   }

   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException {
      // TODO Auto-generated method stub
   }

}