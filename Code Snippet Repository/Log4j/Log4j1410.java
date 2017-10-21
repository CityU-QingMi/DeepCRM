                @Override
                void abbreviate(final int count, final String original, final StringBuilder destination) {
                    // If a path does not contain enough path elements to drop, none will be dropped.
                    int start = 0;
                    int nextStart;
                    for (int i = 0; i < count; i++) {
                        nextStart = original.indexOf('.', start);
                        if (nextStart == -1) {
                            destination.append(original);
                            return;
                        }
                        start = nextStart + 1;
                    }
                    destination.append(original, start, original.length());
                }
