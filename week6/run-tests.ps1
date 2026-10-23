$ErrorActionPreference = 'Stop'
$temporaryParent = [System.IO.Path]::GetFullPath([System.IO.Path]::GetTempPath()).TrimEnd('\', '/')
$buildRoot = Join-Path $temporaryParent ('DAA-week6-' + [guid]::NewGuid().ToString('N'))
New-Item -ItemType Directory -Path $buildRoot | Out-Null

try {
    $sources = @(Get-ChildItem -LiteralPath $PSScriptRoot -Directory |
        Where-Object { $_.Name -ne 'tests' } |
        ForEach-Object { Get-ChildItem -LiteralPath $_.FullName -Filter '*.java' })
    if ($sources.Count -ne 10) {
        throw "Expected 10 solutions, found $($sources.Count)."
    }

    foreach ($source in $sources) {
        $testName = $source.BaseName + 'Test'
        $testFile = Join-Path $PSScriptRoot ('tests/' + $testName + '.java')
        $taskBuild = Join-Path $buildRoot $source.BaseName
        New-Item -ItemType Directory -Path $taskBuild | Out-Null

        & javac -encoding UTF-8 -d $taskBuild $source.FullName `
            (Join-Path $PSScriptRoot 'tests/TreeNode.java') `
            (Join-Path $PSScriptRoot 'tests/TestSupport.java') $testFile
        if ($LASTEXITCODE -ne 0) {
            throw "Compilation failed for $($source.Name)."
        }

        & java -cp $taskBuild $testName
        if ($LASTEXITCODE -ne 0) {
            throw "Tests failed for $($source.Name)."
        }
    }

    Write-Output 'All 10 Week 6 solutions passed.'
} finally {
    if (Test-Path -LiteralPath $buildRoot) {
        $resolvedBuildRoot = (Resolve-Path -LiteralPath $buildRoot).Path
        if ((Split-Path -Parent $resolvedBuildRoot) -ne $temporaryParent -or
            (Split-Path -Leaf $resolvedBuildRoot) -notlike 'DAA-week6-*') {
            throw "Refusing to clean unexpected build path: $resolvedBuildRoot"
        }
        Remove-Item -LiteralPath $resolvedBuildRoot -Recurse -Force
    }
}
