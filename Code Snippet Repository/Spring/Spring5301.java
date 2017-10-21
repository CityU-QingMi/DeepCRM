		@Override
		protected void verify() throws Throwable {
			if (bufferFactory instanceof NettyDataBufferFactory) {
				ByteBufAllocator byteBufAllocator =
						((NettyDataBufferFactory) bufferFactory).getByteBufAllocator();
				if (byteBufAllocator instanceof PooledByteBufAllocator) {
					PooledByteBufAllocator pooledByteBufAllocator =
							(PooledByteBufAllocator) byteBufAllocator;
					PooledByteBufAllocatorMetric metric = pooledByteBufAllocator.metric();
					long allocations = calculateAllocations(metric.directArenas()) +
							calculateAllocations(metric.heapArenas());
					assertTrue("ByteBuf leak detected: " + allocations +
							" allocations were not released", allocations == 0);
				}
			}
		}
