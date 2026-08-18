package br.com.cotiinformatica.api_usuarios.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenComponent {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private String expiration;

    /*
        Método para gerar o TOKEN JWT
     */
    public String generateToken(String usuario, String perfil) {

        //Calcular a data de expiração do TOKEN
        var dataAtual = new Date();
        var dataExpiracao = new Date(dataAtual.getTime() + Integer.parseInt(expiration));

        //Gerando o token
        return Jwts.builder()
                .setSubject(usuario) //Usuário que gerou o TOKEN JWT
                .claim("perfil", perfil) //Perfil do usuário
                .setIssuedAt(dataAtual) //Data e hora de geração do TOKEN
                .setExpiration(dataExpiracao) //Data de exíração do token
                .signWith(SignatureAlgorithm.HS256, secretKey) //Chave secreta para criptografia
                .compact(); //Retornar o TOKEN
    }

    /*
    Método para ler o email do usuário contido no TOKEN JWT
 */
    public String getEmailUsuario(HttpServletRequest http) throws Exception {
        String authorization = http.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }

        String token = authorization.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
