					ExecutableList<AbstractEntityInsertAction> init(ActionQueue instance) {
						if ( instance.isOrderInsertsEnabled() ) {
							return instance.insertions = new ExecutableList<AbstractEntityInsertAction>(
									new InsertActionSorter()
							);
						}
						else {
							return instance.insertions = new ExecutableList<AbstractEntityInsertAction>(
									false
							);
						}
					}
