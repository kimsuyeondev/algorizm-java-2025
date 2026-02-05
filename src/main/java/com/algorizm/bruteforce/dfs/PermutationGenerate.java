package com.algorizm.bruteforce.dfs;

import java.util.*;

public class PermutationGenerate {
      static List<List<Integer>> result = new ArrayList<>();
      static boolean[] used = new boolean[3];
      static int[] nums = {1, 2, 3};

      public static void main(String[] args) {
          dfs(new ArrayList<>());

          System.out.println("=== 모든 순열 ===");
          for (List<Integer> perm : result) {
              System.out.println(perm);
          }
          System.out.println("총 " + result.size() + "개");
      }

      static void dfs(List<Integer> path) {
          if (path.size() == 3) {
              result.add(new ArrayList<>(path));
              return;
          }

          for (int i = 0; i < 3; i++) {
              if (used[i]) continue;
              used[i] = true;
              path.add(nums[i]);
              dfs(path);
              path.remove(path.size() - 1);
              used[i] = false;
          }
      }
  }
