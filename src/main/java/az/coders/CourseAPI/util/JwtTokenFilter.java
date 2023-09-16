package az.coders.CourseAPI.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

private final  JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String username=null;
        String token = jwtUtil.resolveToken(request);
if (token!=null){
     username = jwtUtil.getUsernameToken(token);
}
        if (username != null && token!=null &&
        SecurityContextHolder.getContext().getAuthentication()==null){
    if (jwtUtil.validateToken(token)){
        UsernamePasswordAuthenticationToken uPassToken =
                new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
uPassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
SecurityContextHolder.getContext().setAuthentication(uPassToken);
    }
}
filterChain.doFilter(request,response);
    }
}
