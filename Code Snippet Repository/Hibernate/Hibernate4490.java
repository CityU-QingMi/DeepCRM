	@Override
	@SuppressWarnings("")
	public Stream<R> stream() {
		if (getMaxResults() == 0){
			final Spliterator<R> spliterator = Spliterators.emptySpliterator();
			return StreamSupport.stream( spliterator, false );
		}
		final ScrollableResultsImplementor scrollableResults = scroll( ScrollMode.FORWARD_ONLY );
		final ScrollableResultsIterator<R> iterator = new ScrollableResultsIterator<>( scrollableResults );
		final Spliterator<R> spliterator = Spliterators.spliteratorUnknownSize( iterator, Spliterator.NONNULL );

		final Stream<R> stream = StreamSupport.stream( spliterator, false );
		stream.onClose( scrollableResults::close );

		return stream;
	}
