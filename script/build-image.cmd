CALL "C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC\Auxiliary\Build\vcvars64.bat"

if "%1"=="" (
    echo "Jar file name is not given."
    exit 1
)

if defined PROJECT_HOME (
    set JAR="%PROJECT_HOME%/build/libs/%1"
) else (
    set JAR="./build/libs/%1"
)
echo "Input JAR path: %JAR%"

native-image^
    --no-fallback^
    -jar %JAR%^
    --gc=epsilon^
    -H:IncludeResources="^(version|commit)$"
