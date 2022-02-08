# 日本語用パスワード生成器

秘密の質問を要求してきて、しかも「全角ひらがな」限定のようなサイトがいまだに多く存在するので、
自前で生成器を書きました。


[ダウンロード](https://github.com/minebreaker/japanese-password/releases)


## 使い方

| オプション | デフォルト ||
|:---|:---|:---|
| `l` | 16 | 出力する文字数 |
| `t` | false | 平仮名を含める |
| `k` | false | 片仮名を含める |
| `jis2` | false | 漢字(JIS 0208)を含める |
| `n` | false | 改行を出力しない |

含める文字が指定されていない場合、平仮名、片仮名、漢字(JIS 0208)を含めます

```
> jppass -t
おむきさなてわくへろほへけわほろ

# -nオプションはclipboard-cliのようなユーティリティーと組み合わせると便利です
# が、PowerShellが適切に設定されていないと文字化けする
# npm i -g clipboard-cli
> jppass -t -n | clipboard
```


# TODOs

* Fix the gradle caching meta resource issue
* ci tests for executables
* performance test
