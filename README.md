# dnop

## 環境構築
### ツールの確認
```
git --version
docker --version
docker-compose --version
code --version
```
で全てバージョンが返ってくることを確認する

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
