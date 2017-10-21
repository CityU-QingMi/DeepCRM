                @Override
                void abbreviate(final int count, final String original, final StringBuilder destination) {
                    // We subtract 1 from 'len' when assigning to 'end' to avoid out of
                    // bounds exception in return r.substring(end+1, len). This can happen if
                    // precision is 1 and the category name ends with a dot.
                    int end = original.length() - 1;

                    for (int i = count; i > 0; i--) {
                        end = original.lastIndexOf('.', end - 1);
                        if (end == -1) {
                            destination.append(original);
                            return;
                        }
                    }
                    destination.append(original, end + 1, original.length());
                }
