            @Override
            void escape(final StringBuilder toAppendTo, final int start) {
                for (int i = toAppendTo.length() - 1; i >= start; i--) { // backwards: length may change
                    final char c = toAppendTo.charAt(i);
                    switch (c) {
                        case '\r':
                            toAppendTo.setCharAt(i, '\\');
                            toAppendTo.insert(i + 1, 'r');
                            break;
                        case '\n':
                            toAppendTo.setCharAt(i, '\\');
                            toAppendTo.insert(i + 1, 'n');
                            break;
                        case '&':
                            toAppendTo.setCharAt(i, '&');
                            toAppendTo.insert(i + 1, "amp;");
                            break;
                        case '<':
                            toAppendTo.setCharAt(i, '&');
                            toAppendTo.insert(i + 1, "lt;");
                            break;
                        case '>':
                            toAppendTo.setCharAt(i, '&');
                            toAppendTo.insert(i + 1, "gt;");
                            break;
                        case '"':
                            toAppendTo.setCharAt(i, '&');
                            toAppendTo.insert(i + 1, "quot;");
                            break;
                        case '\'':
                            toAppendTo.setCharAt(i, '&');
                            toAppendTo.insert(i + 1, "apos;");
                            break;
                        case '/':
                            toAppendTo.setCharAt(i, '&');
                            toAppendTo.insert(i + 1, "#x2F;");
                            break;
                    }
                }
            }
