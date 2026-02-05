package com.algorizm;

import java.util.Stack;

class TreeNode {
      int val;
      TreeNode left, right;

      TreeNode(int val) {
          this.val = val;
      }
  }

  public class TreeTraversal {

      // 전위 순회 (재귀) - 매우 간단!
      public static void preorder(TreeNode node) {
          if (node == null) return;

          System.out.print(node.val + " ");
          preorder(node.left);
          preorder(node.right);
      }

      // 전위 순회 (스택) - 복잡함
      public static void preorderStack(TreeNode root) {
          if (root == null) return;

          Stack<TreeNode> stack = new Stack<>();
          stack.push(root);

          while (!stack.isEmpty()) {
              TreeNode node = stack.pop();
              System.out.print(node.val + " ");

              // 오른쪽 먼저 push (나중에 처리되도록)
              if (node.right != null) stack.push(node.right);
              if (node.left != null) stack.push(node.left);
          }
      }

      public static void main(String[] args) {
          TreeNode root = new TreeNode(1);
          root.left = new TreeNode(2);
          root.right = new TreeNode(3);
          root.left.left = new TreeNode(4);
          root.left.right = new TreeNode(5);

          System.out.print("재귀: ");
          preorder(root);
          System.out.print("\n스택: ");
          preorderStack(root);
      }
  }
