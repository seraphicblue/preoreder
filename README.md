# preorder_system
pre-order system
### 컨테이너들 백그라운드 실행
 → docker-compose up -d
 
### 컨테이너들 포어그라운드로 실행
 → docker-compose up # 이미지가 없을 때 이미지를 빌드하고 컨테이너 실행
 → docker-compose up --build # 이미지가 존재하더라도 이미지를 새로 빌드하고 컨테이너 실행
 
### 컨테이너들 정지
 → docker-compose stop # gracefully stop
 → docker-compose stop redis # redis 컨테이너만 정지

### 컨테이너들 내리기 : stop + 컨테이너 삭제
 → docker-compose down

### 정지한 컨테이너들을 재시작
 → docker-compose start
 → docker-compose start redis # redis 컨테이너만 재시작

### 도커 컴포즈 컨테이너들 다시 시작하기
 → docker-compose restart
 → docker-compose restart redis # redis 컨테이너만 재시작

### 실행중인 컨테이너에 명령어 실행하기
docker-compose exec [컨테이너명] [명령어]

### 컨테이너들 로그 확인하기
 → docker-compose logs
 → docker-compose logs -f # 지속적으로 로그 읽기
 → docker-compose logs <컨테이너명> # 지정해서 확인하기
 → docker-compose logs <컨테이너명> <컨테이너명> # 여러개 지정해서 확인하기

### 컨테이너들의 상태 확인
 → docker-compose ps

### 도커 컴포즈 설정을 확인
### 주로 -f 옵션으로 여러 개의 설정 파일을 사용할 때, 최종적으로 어떻게 설정이 적용되는지 확인해볼 때 유용합니다.
 → docker-compose config

### 다른 경로에 있는 도커 컴포즈 파일 사용
### 도커 컴포즈로 다른 이름이나 경로의 파일을 Docker Compose 설정 파일로 사용하고 싶다면 -f 옵션으로 명시를 해줍니다.
 → docker-compose -f /app/docker-compose.yml up
### 여러개의 도커 컴포즈 설정 파일을 사용할 수 있습니다. 이 때는 나중에 나오는 설정이 앞에 나오는 설정보다 우선하게 됩니다.
 → docker-compose -f docker-compose.yml -f docker-compose-test.yml up
