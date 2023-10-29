//main 함수묶음을 정의하기
let main = {
    //초기화 , this를 _this로 접근하도록 변경하기.
    init : function (){
        let _this = this;
        //로그인 버튼을 눌렀을 때 동작
        $('#login_button').on('click', function (){
            _this.login();
        });
    },
    //로그인 데이터 josn post요청하기
    login : function (){
        //데이터 지정
        let data = {
            user_id : $('#user_id').val(), //val()은 값으로 만드는 함수
            user_password : $('#user_password').val()
        };
        //ajax로 요청, ()안에 옵션 헤더의 집합
        $.ajax({
            type: 'POST',
            url: '/api/ver1/login', //  /는 루트 ./현재위치 ../는 상위위치
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function(response) {
                // 성공여부에따라 실행되는 콜백 함수
                let message = response.message;
                alert(message);
            },
            error: function(error) {
                alert(error);
            }
        });
    }
}
main.init();