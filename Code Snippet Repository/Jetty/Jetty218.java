    @Test
    public void testGET() throws Exception {
        URI serverURI = new URI("http://localhost:58080/cdi-webapp/");
        SimpleRequest req = new SimpleRequest(serverURI);
        
        // Typical response:
        // context = ServletContext@o.e.j.w.WebAppContext@37cb63fd{/cdi-webapp,
        // file:///tmp/jetty-0.0.0.0-58080-cdi-webapp.war-_cdi-webapp-any-417759194514596377.dir/webapp/,AVAILABLE}
        // {/cdi-webapp.war}\ncontext.contextPath = /cdi-webapp\ncontext.effective-version = 3.1\n
        assertThat(req.getString("serverinfo"),
                allOf(
                containsString("context = ServletContext@"),
                containsString("context.contextPath = /cdi-webapp"),
                containsString("context.effective-version = 3.1")
                ));
    }
