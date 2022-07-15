# dnop

## 環境構築
### ツールの確認
```
git --version
docker --version
docker-compose --version
code --version
mvn --version
inotifywait -h
```
が実行されることを確認する

### git の認証が完了しているか確認
```
> ssh -T git@github.com
Hi [YOUR NAME]! You've successfully authenticated, but GitHub does not provide shell access.
```
エラーが出るときはssh keyを作るところから
https://qiita.com/shizuma/items/2b2f873a0034839e47ce

### 環境構築

以下のコマンドを実行
```
git clone git@github.com:Engineering-Design-2022/snop-vs.git
cd snop-vs/
code .
```

# 開発用コマンド
- データベース・tomcat のコンテナを立てる
  - `docker-compose up` or `docker-compose up -d`
- コンテナを終了する
  - `docker-compose down`
- ホットコンパイルサーバを立てる
  - `make watch`
- コンパイル + war 化する
  - `make build`
- コンパイルする
  - `make compile`
- war化する
  - `make package`
