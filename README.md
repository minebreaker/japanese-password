# 日本語用パスワード生成器

秘密の質問を要求してきて、しかも「全角ひらがな」限定のようなサイトがいまだに多く存在するので、
自前で生成器を書きました。


| オプション | デフォルト ||
|:---|:---|:---|
| `l` | 16 | 出力する文字数 |
| `k` | false | 片仮名を含める |

```
> jppass -l

```
