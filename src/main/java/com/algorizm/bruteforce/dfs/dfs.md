"구조가 맞는지 확인", "섬 개수 세기", "연결 관계 판단" 같은 문제

BFS는 재귀로 못한다 → 그래서 큐를 직접 만들어야 함
DFS는 재귀로 가능 → 그래서 스택을 직접 만들지 않아도 됨 (생각해보니 미로찾기 뿐만아니라 피보나치도 예시가되겟네)

핵심 개념 하나로 설명

DFS는:

“갈 수 있을 만큼 끝까지 내려갔다가
막히면 뒤로 돌아오는 탐색”

핵심 패턴 (암기)
void dfs(int node) {
    visited[node] = true;
    for (int next : graph[node]) {
        if (!visited[next]) {
            dfs(next);
        }
    }
}

예시 1) 그래프가 사이클이 있는지 검사

기사/요청/연결 정보에서 잘못된 연결 체크 같은 문제에서 출제 가능

boolean dfs(int v, int parent) {
    visited[v] = true;
    for (int nxt : graph[v]) {
        if (!visited[nxt]) {
            if (dfs(nxt, v)) return true;       // cycle found
        } else if (nxt != parent) {
            return true;
        }
    }
    return false;
}

예시 2) 배열에서 “연속된 영역 개수 세기” (백엔드 실무에서 자주 쓰임)
예: 지도에서 IDLE인 영역이 몇 개인지 세라 → DFS
(배차 구역 체크 같은 문제)

void dfs(int r, int c) {
    if (outOfRange) return;
    if (visited[r][c] || map[r][c] == 0) return;
    visited[r][c] = true;
    dfs(r+1,c);
    dfs(r-1,c);
    dfs(r,c+1);
    dfs(r,c-1);
}
--------
Stack<int[]> stack = new Stack<>();
stack.push(new int[]{r, c});

while (!stack.isEmpty()) {
    int[] cur = stack.pop();
    int x = cur[0];
    int y = cur[1];
    if (visited[x][y]) continue;
    visited[x][y] = true;
    stack.push(new int[]{x+1, y});
    stack.push(new int[]{x-1, y});
    stack.push(new int[]{x, y+1});
    stack.push(new int[]{x, y-1});
}
--------------
1 1 0 0
0 1 0 0
0 0 1 1
여기서 1로 연결된 덩어리가 몇 개인지 찾으래.
(0,0)~(1,1) → 하나의 영역
(2,2)~(2,3) → 또 하나의 영역
총 2개
이걸 찾는 게 DFS.

DFS 코드 (진짜 최기초)
boolean[][] visited;
int[][] map;

void dfs(int r, int c) {
// 1) 범위 밖이면 무시
if (r < 0 || c < 0 || r >= map.length || c >= map[0].length) return;

// 2) 이미 방문했거나 0이면 무시
if (visited[r][c] || map[r][c] == 0) return;
// 3) 방문 표시
visited[r][c] = true;

// 4) 4방향 탐색
    dfs(r+1, c);
    dfs(r-1, c);
    dfs(r, c+1);
    dfs(r, c-1);
}
