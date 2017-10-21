	public void testValidToken() {
        String tokenName = "validTokenTest";
        String token = TokenHelper.setToken(tokenName);
		final String sessionTokenName = TokenHelper.buildTokenSessionAttributeName(tokenName);
		assertEquals(token, session.get(sessionTokenName));

        Map<String, String[]> params = new HashMap<>();
        params.put(TokenHelper.TOKEN_NAME_FIELD, new String[]{tokenName});
        params.put(tokenName, new String[]{token});

        ActionContext.getContext().setParameters(HttpParameters.create(params).build());

        assertTrue(TokenHelper.validToken());
    }
