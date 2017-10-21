    private String doTokenTest(String tokenName, TokenTag tag) {
        tag.setPageContext(pageContext);

        String token = null;

        try {
            tag.doStartTag();
            tag.doEndTag();

            token = (String) context.get(tokenName);
			assertNotNull(token);
			final String sessionTokenName = TokenHelper.buildTokenSessionAttributeName(tokenName);
			assertEquals(token, pageContext.getSession().getAttribute(sessionTokenName));
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        return token;
    }
