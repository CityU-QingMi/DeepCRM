	public RxNettyWebSocketSession aggregateFrames(Channel channel, String frameDecoderName) {
		ChannelPipeline pipeline = channel.pipeline();
		if (pipeline.context(FRAME_AGGREGATOR_NAME) != null) {
			return this;
		}
		ChannelHandlerContext frameDecoder = pipeline.context(frameDecoderName);
		if (frameDecoder == null) {
			throw new IllegalArgumentException("WebSocketFrameDecoder not found: " + frameDecoderName);
		}
		ChannelHandler frameAggregator = new WebSocketFrameAggregator(DEFAULT_FRAME_MAX_SIZE);
		pipeline.addAfter(frameDecoder.name(), FRAME_AGGREGATOR_NAME, frameAggregator);
		return this;
	}
