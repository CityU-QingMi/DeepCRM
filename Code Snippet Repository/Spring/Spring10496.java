	@Test
	public void deleteCallback() throws Exception  {
		ListenableFuture<?> deletedFuture = template.delete(new URI(baseUrl + "/delete"));
		deletedFuture.addCallback(new ListenableFutureCallback<Object>() {
			@Override
			public void onSuccess(Object result) {
				assertNull(result);
			}
			@Override
			public void onFailure(Throwable ex) {
				fail(ex.getMessage());
			}
		});
		waitTillDone(deletedFuture);
	}
