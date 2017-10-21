	public static void broadcastEvictAll(AdvancedCache cache) {
		final RpcManager rpcManager = cache.getRpcManager();
		if ( rpcManager != null ) {
			// Only broadcast evict all if it's clustered
			final CacheCommandInitializer factory = cache.getComponentRegistry()
					.getComponent( CacheCommandInitializer.class );
			final boolean isSync = isSynchronousCache( cache );

			final EvictAllCommand cmd = factory.buildEvictAllCommand( cache.getName() );
			final RpcOptions options = rpcManager.getDefaultRpcOptions( isSync );
			rpcManager.invokeRemotely( null, cmd, options );
		}
	}
