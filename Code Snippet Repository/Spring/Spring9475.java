	@Override
	public Mono<Void> writeWith(File file, long position, long count) {
		return doCommit(() -> {
			FileChannel source = null;
			try {
				source = FileChannel.open(file.toPath(), StandardOpenOption.READ);
				StreamSinkChannel destination = this.exchange.getResponseChannel();
				Channels.transferBlocking(destination, source, position, count);
				return Mono.empty();
			}
			catch (IOException ex) {
				return Mono.error(ex);
			}
			finally {
				if (source != null) {
					try {
						source.close();
					}
					catch (IOException ex) {
						// ignore
					}
				}
			}
		});
	}
