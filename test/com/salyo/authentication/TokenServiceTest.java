package com.salyo.authentication;

import org.junit.Test;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TokenServiceTest {
    @Test
    public void createToken() throws Exception {
        System.out.println(TokenService.getInstance().createToken("dleyendecker"));

    }

}