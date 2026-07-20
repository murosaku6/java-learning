# 成績管理システム

## 概要

Spring Bootを用いて開発する学校向けの成績管理システムです。

学生・科目・成績を一元管理し、
平均点やランキングの表示、
成績表のPDF出力などを行うことができます。

本システムはMVCモデルを採用し、
Spring Boot・Thymeleafを用いたWebアプリケーションとして開発します。

---

# 開発目的

Javaのオブジェクト指向を活かした設計を実践するとともに、
Spring Bootを利用したWebアプリケーション開発を経験することを目的としています。

また、Controller・Service・Modelの役割を意識した設計や、
実務を想定した保守性・可読性の高いコードを書くことを目標としています。

さらに、後からデータベース(MySQL)へ移行できる構成で開発することで、
拡張性の高いシステムを目指します。

---

# 開発環境

- Java
- Spring Boot
- Thymeleaf
- HTML
- CSS
- Bootstrap
- Git
- GitHub
- VS Code

※データは開発初期ではArrayListで管理し、
後にMySQLへ移行予定です。

---

# システム構成

```
Controller
    ↓
Service
    ↓
Model
```

MVCアーキテクチャを採用しています。

---

# 主な機能

## 学生管理

- 学生登録
- 学生編集
- 学生削除
- 学生一覧表示
- CSV一括取込
- CSV出力
 

---

## 科目管理

- 科目登録
- 科目編集
- 科目削除
- 科目一覧表示

---

## 成績管理

- 成績登録
- 成績編集
- 成績削除
- 成績一覧表示

---

## 検索機能

- 学生名検索
- 学年検索
- クラス検索
- 科目検索

---

## 統計機能

- 学生平均点
- 科目平均点
- 学年平均点
- クラス平均点
- 全体平均点
- 最高点
- 最低点

---

## ランキング機能

- 個人ランキング
- クラスランキング
- 学年ランキング

---

## PDF出力

- 個人成績表PDF出力
- クラス成績一覧PDF出力

---

## CSV機能

- 学生CSV取込
- 学生CSV出力
- 成績CSV出力

---

# クラス構成

## Model

- Student
- Subject
- Score

---

## Controller

- HomeController
- StudentController
- SubjectController
- ScoreController
- StatisticsController
- PdfController

---

## Service

- StudentService
- SubjectService
- ScoreService
- StatisticsService
- PdfService

---

# ディレクトリ構成

```
GradeManagementSystem
│
├── controller
├── service
├── repository
├── model
├── templates
├── static
└── GradeManagementApplication
```

---

# 今後の拡張予定

- MySQLとの連携
- Spring Data JPA
- ログイン機能
- ユーザー権限管理
- CSV出力
- PDFレイアウト改善

---

# 学習目標

- Spring BootによるWebアプリケーション開発
- MVC設計
- Thymeleaf
- Bootstrap
- 保守性を意識した設計
- リファクタリング
- 実務を意識したコーディング

---

# ライセンス

このプロジェクトは学習目的で作成しています。