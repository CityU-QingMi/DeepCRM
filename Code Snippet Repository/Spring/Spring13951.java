	private ServerInfo getServerInfo(SockJsUrlInfo sockJsUrlInfo, @Nullable HttpHeaders headers) {
		URI infoUrl = sockJsUrlInfo.getInfoUrl();
		ServerInfo info = this.serverInfoCache.get(infoUrl);
		if (info == null) {
			long start = System.currentTimeMillis();
			String response = this.infoReceiver.executeInfoRequest(infoUrl, headers);
			long infoRequestTime = System.currentTimeMillis() - start;
			info = new ServerInfo(response, infoRequestTime);
			this.serverInfoCache.put(infoUrl, info);
		}
		return info;
	}
