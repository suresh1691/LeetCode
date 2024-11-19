/* APPROACH 1*/
// Approach is first we iterate and remove the close parenthis and add remaining elements in the StringBuilder1.
//then iterate that String BUilder and check if opened count is not zero and contains unclosed open parenthesis remove that.

public class Solution{
  public String minRemoveToMakeValid(String s)
    {
          StringBuilder sb = new StringBuilder();
          int open = 0;
          for(int i = 0 ; i < s.length() ; i++)
          {
                char c = s.charAt(i);
                if(c == '(')
                {
                    open++;
                }
                else if(c == ')')
                {
                    if(open == 0) continue;
                    open--;
                }

                sb.append(c);
          }

          StringBuilder res = new StringBuilder();
          for(int i = sb.length()-1 ; i >= 0 ; i--)
          {
            if(sb.charAt(i) == '('  && open--> 0) continue;
            res.append(sb.charAt(i));
          }

          return res.reverse().toString();

    }
}

============================================================================

/* APPROACH 2*/

  //Use stack and add open char index values in the stack and if we find close parenthesis remove it from stack .. if stack is empty then replace that char with # 
  // check if the stack is not empty meaning open parenthesis we need to rmeove .. iterate the stack and replace the char with # .. finally replace all # with empty space.

class Solution {
  public String minRemoveToMakeValid(String s) {
    Deque<Integer> stack = new ArrayDeque<>(); // record '(' indices
    StringBuilder sb = new StringBuilder(s);

    for (int i = 0; i < s.length(); ++i)
      if (sb.charAt(i) == '(') {
        stack.push(i); // add the unpaired '(' index.
      } else if (sb.charAt(i) == ')') {
        if (stack.isEmpty())
          sb.setCharAt(i, '#'); // Mark the unpaired ')' as '#'.
        else
          stack.pop(); // Find a pair!
      }

    // Mark the unpaired '(' as '#'.
    while (!stack.isEmpty())
      sb.setCharAt(stack.pop(), '#');

    return sb.toString().replaceAll("#", "");
  }
}
  
