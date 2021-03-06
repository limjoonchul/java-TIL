# [2*n 타일링](https://programmers.co.kr/learn/courses/30/lessons/12900) 문제풀이

 * 이런 문제가 알고나면 화가나는 문제중 하나! 
 *다이나믹 프로그래밍 (동적계획법) - 최적화 기법 중 하나 이름은 역사적으로 붙음 (의미자체는 별 의미가 없음)
 * 핵심 반복적인 계산이 있으면 반복적인 계산을 하지 않고, 저장하고 저장된걸 참조해서 한다.
 * n개를 채우는데 있어서 f(n)이라고 표현을 했을 때 점화식을 표현하는게 중요
 * n을 더 쉬운 방법으로 바꿔서 사용하는것 이게 점화식이다.
 * n개를 채우는 방법은 가로 1짜리로 앞에를 채웠을 때 나머지는 n-1이다.
 * 가로로 채웠을경우 2짜리로 했을 경우 n-2경우와 같다.
 * f(n) = f(n-1) + f(n-2); 아무리 큰수가 들어와도 작은 수로 표현이 됨
 * 초기값 두개만 알고 있으면 됨. n이 1인 경우와 2인 경우를 알고 있으면 3을 알 수 있고 2,3은 4를 알 수 있고 ..
 * f(1) = 1 가지 방법
 * f(2) = 2; 가지 방법
 * f(3) = f(1) + f(2);
 * 피보나치수열과 완전히 같음 피보나치는 0,1로시작하지만 이건 1,2로 시작함
 * 점화식을 이용했다 배열을 만들어 놓고 이용을 하더라 정도 생각 하면 됨.
 * 재귀함수의 장점 구현을 빠르게할 수 있음, 더 짧게 구현이 가능하다.
 * 클린코드에 관해서 함수의 단위를 줄여야 하는 부분이 있는데
 * for문을 사용하는게 iterative하게구현하는 것은 코드 함수 자체의
 * 복잡도는 증가하는 단점이 있는데 리컬시브는 심플하고 간단하게 되는 장점이 있음
 * 스택이 쌓이는 경우가 많지 않은 경우 재귀 함수를 쓰는 일이 많이 있음.

 
 ````groovy
public class Tile {
    public int solution(int n){
        int [] integers = new int[n+1];
        // 60000까지 계싼을해야하는데
        //n이 1부터시작하니깐 n+1로 해야 n까지사용할 수 있음

        integers[1] = 1;
        integers[2] = 2;
        for (int i =3; i<n+1; i++){
            integers[i] = (integers[i-1]+integers[i-2]) % 1000000007;
        }
        return integers[n]; //이렇게만했을때 오버플러우 발생
        //그래서 값을 나눠줘야한다.

//        제한사항에 60000까지인데 재귀함수 형태로 사용하지 못함
//        if(n == 1){
//            return 1;
//        }
//        if(n == 2){
//            return 2;
//        }
//        return solution(n-1)+solution(n-2);
    }

    public static void main(String[] args) {
        new Tile().solution(4);
    }
}
````

