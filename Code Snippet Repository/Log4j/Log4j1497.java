                public String toString() {
                    if (!Ansi.this.enabled()) {
                        return plain.toString().substring(from, from + length);
                    }
                    if (length == 0) { return ""; }
                    StringBuilder sb = new StringBuilder(plain.length() + 20 * indexToStyle.size());
                    Integer startStyle = null;
                    Integer endStyle = -1;
                    for (Integer index : indexToStyle.keySet()) {
                        if (index <= from) {
                            startStyle = startStyle == null ? index : null;
                            endStyle   = endStyle   == null ? index : null;
                        }
                        if (index >= from) {break;}
                    }
                    if (startStyle != null) {
                        sb.append(indexToStyle.get(startStyle));
                        endStyle = startStyle;
                    }
                    int end = Math.min(from + length, plain.length());
                    for (int i = from; i < end; i++) {
                        String style = indexToStyle.get(i);
                        if (style != null) {
                            if (endStyle != null && endStyle != i) {
                                sb.append(style);
                                startStyle = startStyle == null ? i : null;
                            }
                            endStyle = i;
                        }
                        sb.append(plain.charAt(i));
                    }
                    if (startStyle != null) { // find closing style
                        SortedMap<Integer, String> tailMap = indexToStyle.tailMap(startStyle + 1);
                        if (!tailMap.isEmpty()) {
                            sb.append(indexToStyle.get(tailMap.firstKey()));
                        } else {
                            sb.append(Style.reset.off());
                        }
                    }
                    return sb.toString();
                }
