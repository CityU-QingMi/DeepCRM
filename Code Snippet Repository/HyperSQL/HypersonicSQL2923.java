    public void checkGranteeList(OrderedHashSet granteeList) {

        for (int i = 0; i < granteeList.size(); i++) {
            String  name    = (String) granteeList.get(i);
            Grantee grantee = get(name);

            if (grantee == null) {
                throw Error.error(ErrorCode.X_28501, name);
            }

            if (isImmutable(name)) {
                throw Error.error(ErrorCode.X_28502, name);
            }

            if (grantee instanceof User && ((User) grantee).isExternalOnly) {
                throw Error.error(ErrorCode.X_28000, name);
            }
        }
    }
