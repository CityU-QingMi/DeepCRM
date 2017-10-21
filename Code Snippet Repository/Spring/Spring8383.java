	@Test
	public void removeAttributeWithNoScopeSpecifiedRemovesValueFromAllScopes() throws Exception {
		ctx.setAttribute(key, value, PageContext.APPLICATION_SCOPE);
		ctx.removeAttribute(key);

		assertNull(ctx.getAttribute(key, PageContext.PAGE_SCOPE));
		assertNull(ctx.getAttribute(key, PageContext.APPLICATION_SCOPE));
		assertNull(ctx.getAttribute(key, PageContext.REQUEST_SCOPE));
		assertNull(ctx.getAttribute(key, PageContext.SESSION_SCOPE));
	}
