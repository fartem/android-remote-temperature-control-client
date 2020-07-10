<img src="media/logo/ic_app.png" height="100px" />

# Android Remote Temperature Control Client

[![Travis CI](https://travis-ci.org/fartem/android-remote-temperature-control-client.svg?branch=master)](https://travis-ci.org/fartem/android-remote-temperature-control-client)
[![Codebeat](https://codebeat.co/badges/18d9fcff-7f58-4b78-943e-47bc4d091238)](https://codebeat.co/projects/github-com-fartem-android-remote-temperature-control-client-master)
[![Codecov](https://codecov.io/gh/fartem/android-remote-temperature-control-client/branch/master/graph/badge.svg)](https://codecov.io/gh/fartem/android-remote-temperature-control-client)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20Home%20Control%20Client-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/7943)

## About

Remote client for [Arduino temperature project](https://github.com/fartem/arduino-temperature-control).

## How to run

__With Arduino module__

1. run [Arduino module](https://github.com/fartem/arduino-temperature-control);
2. pair your Android device and Arduino Bluetooth module from a device settings;
3. install `devicebt` app flavor;
4. run app and select Arduino Bluetooth module from devices list.

__Without Arduino module__

Install `debugbt` app flavor.

## Download

<img src="media/qrcodes/github_download.png" height="150px" />

## Screenshots

<br/>
<p align="center">
  <img src="media/screenshots/screenshot_01.png" width="190" />
  <img src="media/screenshots/screenshot_02.png" width="190" />
</p>

## How to contribute

Read [Commit Convention](https://github.com/fartem/repository-rules/blob/master/commit-convention/COMMIT_CONVENTION.md). Make sure your build is green before you contribute your pull request. Then:

```shell
$ ./gradlew clean
$ ./gradlew build
```

If you don't see any error messages, submit your pull request.

## Contributors

* [@fartem](https://github.com/fartem) as Artem Fomchenkov
