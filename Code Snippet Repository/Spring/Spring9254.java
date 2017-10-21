		public OkHttpListenableFuture(Call call) {
			this.call = call;
			this.call.enqueue(new Callback() {
				@Override
				public void onResponse(Call call, Response response) {
					set(new OkHttp3ClientHttpResponse(response));
				}
				@Override
				public void onFailure(Call call, IOException ex) {
					setException(ex);
				}
			});
		}
