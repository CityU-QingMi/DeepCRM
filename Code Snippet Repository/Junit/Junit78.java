	void resolveUniqueId(UniqueId uniqueId) {
		uniqueId.getEngineId().ifPresent(engineId -> {

			// Ignore Unique IDs from other test engines.
			if (JupiterTestEngine.ENGINE_ID.equals(engineId)) {
				List<Segment> remainingSegments = new ArrayList<>(uniqueId.getSegments());

				// Ignore engine ID
				remainingSegments.remove(0);

				int numSegmentsToResolve = remainingSegments.size();
				int numSegmentsResolved = resolveUniqueId(this.engineDescriptor, remainingSegments);

				if (numSegmentsResolved == 0) {
					logger.warn(() -> format("Unique ID '%s' could not be resolved.", uniqueId));
				}
				else if (numSegmentsResolved != numSegmentsToResolve) {
					logger.warn(() -> {
						List<Segment> segments = uniqueId.getSegments();
						List<Segment> unresolved = segments.subList(1, segments.size()); // Remove engine ID
						unresolved = unresolved.subList(numSegmentsResolved, unresolved.size()); // Remove resolved segments
						return format("Unique ID '%s' could only be partially resolved. "
								+ "All resolved segments will be executed; however, the "
								+ "following segments could not be resolved: %s",
							uniqueId, unresolved);
					});
				}
			}
		});
	}
