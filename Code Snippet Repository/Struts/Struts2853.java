        public Vector getScriptingVars(int scope) {
            Vector vec = null;

            switch (scope) {
            case VariableInfo.AT_BEGIN:
                vec = this.atBeginScriptingVars;
                break;
            case VariableInfo.AT_END:
                vec = this.atEndScriptingVars;
                break;
            case VariableInfo.NESTED:
                vec = this.nestedScriptingVars;
                break;
            }

            return vec;
        }
