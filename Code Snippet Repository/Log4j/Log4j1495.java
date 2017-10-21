                public Text append(Text other) {
                    Text result = (Text) clone();
                    result.plain = new StringBuilder(plain.toString().substring(from, from + length));
                    result.from = 0;
                    result.indexToStyle = new TreeMap<Integer, String>();
                    for (Integer index : indexToStyle.keySet()) {
                        result.indexToStyle.put(index - from, indexToStyle.get(index));
                    }
                    result.plain.append(other.plain.toString().substring(other.from, other.from + other.length));
                    for (Integer otherIndex : other.indexToStyle.keySet()) {
                        int index = result.length + otherIndex - other.from;
                        String replaced = result.indexToStyle.put(index, other.indexToStyle.get(otherIndex));
                        if (replaced != null) {
                            result.indexToStyle.put(index, replaced + other.indexToStyle.get(otherIndex));
                        }
                    }
                    result.length = result.plain.length();
                    return result;
                }
