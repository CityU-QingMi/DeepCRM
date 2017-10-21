        private String matchMap(ArrayList functions) {

            String mapName = null;
            for (int i = 0; i < functions.size(); i++) {
                ELNode.Function f = (ELNode.Function)functions.get(i);
                String temName = (String) gMap.get(f.getPrefix() + ':' +
                                        f.getName() + ':' + f.getUri());
                if (temName == null) {
                    return null;
                }
                if (mapName == null) {
                    mapName = temName;
                } else if (!temName.equals(mapName)) {
                    // If not all in the previous match, then no match.
                    return null;
                }
            }
            return mapName;
        }
