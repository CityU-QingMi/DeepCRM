    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        OpenIDAuthenticationToken auth;

        // Processing standard OpenId user authentication    
        auth = (OpenIDAuthenticationToken) super.attemptAuthentication(req, rsp);

        // auth will be null on the first pass of super.attemptAuthentication()
        if (auth != null) {
            GrantedAuthority ga = (GrantedAuthority) auth.getAuthorities().toArray()[0];

            if (ga.getAuthority().equals("rollerOpenidLogin")) {

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/

            }
        }
        return auth;
    }
