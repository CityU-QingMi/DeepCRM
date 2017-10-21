        public void setScriptingVars(Vector vec, int scope) {
            switch (scope) {
            case VariableInfo.AT_BEGIN:
                this.atBeginScriptingVars = vec;
                break;
            case VariableInfo.AT_END:
                this.atEndScriptingVars = vec;
                break;
            case VariableInfo.NESTED:
                this.nestedScriptingVars = vec;
                break;
            }
        }
