	@Parameterized.Parameters(name = "")
	public static Object[][] dataBufferFactories() {
		return new Object[][] {
				{new NettyDataBufferFactory(new UnpooledByteBufAllocator(true))},
				{new NettyDataBufferFactory(new UnpooledByteBufAllocator(false))},
				// disable caching for reliable leak detection, see https://github.com/netty/netty/issues/5275
				{new NettyDataBufferFactory(new PooledByteBufAllocator(true, 1, 1, 8192, 11, 0, 0, 0, true))},
				{new NettyDataBufferFactory(new PooledByteBufAllocator(false, 1, 1, 8192, 11, 0, 0, 0, true))},
				{new DefaultDataBufferFactory(true)},
				{new DefaultDataBufferFactory(false)}

		};
	}
