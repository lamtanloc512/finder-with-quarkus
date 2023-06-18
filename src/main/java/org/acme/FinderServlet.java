package org.acme;

import com.cksource.ckfinder.servlet.CKFinderServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "FinderServlet",urlPatterns = {"/ckfinder/*"}, initParams = {
        @WebInitParam(name = "scan-path", value = "org.acme")
})
public class FinderServlet extends CKFinderServlet {}
