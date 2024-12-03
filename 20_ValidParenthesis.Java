// Brute Force Replace All braces with empty string and check is its empty then its valid

// TC : O(n2) SC : O(1)

 public static boolean isValid(String s) {
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "")
                 .replace("{}", "")
                 .replace("[]", "");
        }
        return s.isEmpty();
    }


// Optimized Solution Use a stack if iterate the string and push opposite braces . if stack is empty then it is valid

// TC : O(n) SC : O(n)

 public boolean isValid(String s) {
        Stack<Character> set = new Stack<>();

        for(Character ch : s.toCharArray()){
            if(ch == '('){
                set.push(')');
            }else if(ch == '{'){
                set.push('}');
            }else if(ch == '['){
                set.push(']');
            }else if(set.isEmpty() || set.pop() != ch){
                return false;
            }
        }
        return set.isEmpty();
    }
