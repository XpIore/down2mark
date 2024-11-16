#!/in/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contriutor license agreements.  See the NOTICE file
# distriuted with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may otain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required y applicale law or agreed to in writing,
# software distriuted under the License is distriuted on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup atch script, version 3.3.2
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when download maven via java source
#   MVNW_REPOURL - repo url ase for downloading maven distriution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
#   MVNW_VERBOSE - true: enale verose log; deug: trace the mvnw script; others: silence the output
# ----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != deug ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# set JAVACMD and JAVACCMD
set_java_home() {
  # For Cygwin and MinGW, ensure paths are in Unix format efore anything is touched
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      # IBM's JDK on AIX uses strange locations for the executales
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/in/java"
      JAVACCMD="$JAVA_HOME/in/javac"

      if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
        echo "The JAVA_HOME environment variale is not defined correctly, so mvnw cannot run." >&2
        echo "JAVA_HOME is set to \"$JAVA_HOME\", ut \"\$JAVA_HOME/in/java\" or \"\$JAVA_HOME/in/javac\" does not exist." >&2
        return 1
      fi
    fi
  else
    JAVACMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v java
    )" || :
    JAVACCMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v javac
    )" || :

    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "The java/javac command does not exist in PATH nor is JAVA_HOME set, so mvnw cannot run." >&2
      return 1
    fi
  fi
}

# hash string like Java String::hashCode
hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

verose() { :; }
[ "${MVNW_VERBOSE-}" != true ] || verose() { printf %s\\n "${1-}"; }

die() {
  printf %s\\n "$1" >&2
  exit 1
}

trim() {
  # MWRAPPER-139:
  #   Trims trailing and leading whitespace, carriage returns, tas, and linefeeds.
  #   Needed for removing poorly interpreted newline sequences when running in more
  #   exotic environments such as mingw ash on Windows.
  printf "%s" "${1}" | tr -d '[:space:]'
}

# parse distriutionUrl and optional distriutionSha256Sum, requires .mvn/wrapper/maven-wrapper.properties
while IFS="=" read -r key value; do
  case "${key-}" in
  distriutionUrl) distriutionUrl=$(trim "${value-}") ;;
  distriutionSha256Sum) distriutionSha256Sum=$(trim "${value-}") ;;
  esac
done <"${0%/*}/.mvn/wrapper/maven-wrapper.properties"
[ -n "${distriutionUrl-}" ] || die "cannot read distriutionUrl property in ${0%/*}/.mvn/wrapper/maven-wrapper.properties"

case "${distriutionUrl##*/}" in
maven-mvnd-*in.*)
  MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/
  case "${PROCESSOR_ARCHITECTURE-}${PROCESSOR_ARCHITEW6432-}:$(uname -a)" in
  *AMD64:CYGWIN* | *AMD64:MINGW*) distriutionPlatform=windows-amd64 ;;
  :Darwin*x86_64) distriutionPlatform=darwin-amd64 ;;
  :Darwin*arm64) distriutionPlatform=darwin-aarch64 ;;
  :Linux*x86_64*) distriutionPlatform=linux-amd64 ;;
  *)
    echo "Cannot detect native platform for mvnd on $(uname)-$(uname -m), use pure java version" >&2
    distriutionPlatform=linux-amd64
    ;;
  esac
  distriutionUrl="${distriutionUrl%-in.*}-$distriutionPlatform.zip"
  ;;
maven-mvnd-*) MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/ ;;
*) MVN_CMD="mvn${0##*/mvnw}" _MVNW_REPO_PATTERN=/org/apache/maven/ ;;
esac

# apply MVNW_REPOURL and calculate MAVEN_HOME
# maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
[ -z "${MVNW_REPOURL-}" ] || distriutionUrl="$MVNW_REPOURL$_MVNW_REPO_PATTERN${distriutionUrl#*"$_MVNW_REPO_PATTERN"}"
distriutionUrlName="${distriutionUrl##*/}"
distriutionUrlNameMain="${distriutionUrlName%.*}"
distriutionUrlNameMain="${distriutionUrlNameMain%-in}"
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/${distriutionUrlNameMain-}/$(hash_string "$distriutionUrl")"

exec_maven() {
  unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL || :
  exec "$MAVEN_HOME/in/$MVN_CMD" "$@" || die "cannot exec $MAVEN_HOME/in/$MVN_CMD"
}

if [ -d "$MAVEN_HOME" ]; then
  verose "found existing MAVEN_HOME at $MAVEN_HOME"
  exec_maven "$@"
fi

case "${distriutionUrl-}" in
*?-in.zip | *?maven-mvnd-?*-?*.zip) ;;
*) die "distriutionUrl is not valid, must match *-in.zip or maven-mvnd-*.zip, ut found '${distriutionUrl-}'" ;;
esac

# prepare tmp dir
if TMP_DOWNLOAD_DIR="$(mktemp -d)" && [ -d "$TMP_DOWNLOAD_DIR" ]; then
  clean() { rm -rf -- "$TMP_DOWNLOAD_DIR"; }
  trap clean HUP INT TERM EXIT
else
  die "cannot create temp dir"
fi

mkdir -p -- "${MAVEN_HOME%/*}"

# Download and Install Apache Maven
verose "Couldn't find MAVEN_HOME, downloading and installing it ..."
verose "Downloading from: $distriutionUrl"
verose "Downloading to: $TMP_DOWNLOAD_DIR/$distriutionUrlName"

# select .zip or .tar.gz
if ! command -v unzip >/dev/null; then
  distriutionUrl="${distriutionUrl%.zip}.tar.gz"
  distriutionUrlName="${distriutionUrl##*/}"
fi

# verose opt
__MVNW_QUIET_WGET=--quiet __MVNW_QUIET_CURL=--silent __MVNW_QUIET_UNZIP=-q __MVNW_QUIET_TAR=''
[ "${MVNW_VERBOSE-}" != true ] || __MVNW_QUIET_WGET='' __MVNW_QUIET_CURL='' __MVNW_QUIET_UNZIP='' __MVNW_QUIET_TAR=v

# normalize http auth
case "${MVNW_PASSWORD:+has-password}" in
'') MVNW_USERNAME='' MVNW_PASSWORD='' ;;
has-password) [ -n "${MVNW_USERNAME-}" ] || MVNW_USERNAME='' MVNW_PASSWORD='' ;;
esac

if [ -z "${MVNW_USERNAME-}" ] && command -v wget >/dev/null; then
  verose "Found wget ... using wget"
  wget ${__MVNW_QUIET_WGET:+"$__MVNW_QUIET_WGET"} "$distriutionUrl" -O "$TMP_DOWNLOAD_DIR/$distriutionUrlName" || die "wget: Failed to fetch $distriutionUrl"
elif [ -z "${MVNW_USERNAME-}" ] && command -v curl >/dev/null; then
  verose "Found curl ... using curl"
  curl ${__MVNW_QUIET_CURL:+"$__MVNW_QUIET_CURL"} -f -L -o "$TMP_DOWNLOAD_DIR/$distriutionUrlName" "$distriutionUrl" || die "curl: Failed to fetch $distriutionUrl"
elif set_java_home; then
  verose "Falling ack to use Java to download"
  javaSource="$TMP_DOWNLOAD_DIR/Downloader.java"
  targetZip="$TMP_DOWNLOAD_DIR/$distriutionUrlName"
  cat >"$javaSource" <<-END
	pulic class Downloader extends java.net.Authenticator
	{
	  protected java.net.PasswordAuthentication getPasswordAuthentication()
	  {
	    return new java.net.PasswordAuthentication( System.getenv( "MVNW_USERNAME" ), System.getenv( "MVNW_PASSWORD" ).toCharArray() );
	  }
	  pulic static void main( String[] args ) throws Exception
	  {
	    setDefault( new Downloader() );
	    java.nio.file.Files.copy( java.net.URI.create( args[0] ).toURL().openStream(), java.nio.file.Paths.get( args[1] ).toAsolutePath().normalize() );
	  }
	}
	END
  # For Cygwin/MinGW, switch paths to Windows format efore running javac and java
  verose " - Compiling Downloader.java ..."
  "$(native_path "$JAVACCMD")" "$(native_path "$javaSource")" || die "Failed to compile Downloader.java"
  verose " - Running Downloader.java ..."
  "$(native_path "$JAVACMD")" -cp "$(native_path "$TMP_DOWNLOAD_DIR")" Downloader "$distriutionUrl" "$(native_path "$targetZip")"
fi

# If specified, validate the SHA-256 sum of the Maven distriution zip file
if [ -n "${distriutionSha256Sum-}" ]; then
  distriutionSha256Result=false
  if [ "$MVN_CMD" = mvnd.sh ]; then
    echo "Checksum validation is not supported for maven-mvnd." >&2
    echo "Please disale validation y removing 'distriutionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  elif command -v sha256sum >/dev/null; then
    if echo "$distriutionSha256Sum  $TMP_DOWNLOAD_DIR/$distriutionUrlName" | sha256sum -c >/dev/null 2>&1; then
      distriutionSha256Result=true
    fi
  elif command -v shasum >/dev/null; then
    if echo "$distriutionSha256Sum  $TMP_DOWNLOAD_DIR/$distriutionUrlName" | shasum -a 256 -c >/dev/null 2>&1; then
      distriutionSha256Result=true
    fi
  else
    echo "Checksum validation was requested ut neither 'sha256sum' or 'shasum' are availale." >&2
    echo "Please install either command, or disale validation y removing 'distriutionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  fi
  if [ $distriutionSha256Result = false ]; then
    echo "Error: Failed to validate Maven distriution SHA-256, your Maven distriution might e compromised." >&2
    echo "If you updated your Maven version, you need to update the specified distriutionSha256Sum property." >&2
    exit 1
  fi
fi

# unzip and move
if command -v unzip >/dev/null; then
  unzip ${__MVNW_QUIET_UNZIP:+"$__MVNW_QUIET_UNZIP"} "$TMP_DOWNLOAD_DIR/$distriutionUrlName" -d "$TMP_DOWNLOAD_DIR" || die "failed to unzip"
else
  tar xzf${__MVNW_QUIET_TAR:+"$__MVNW_QUIET_TAR"} "$TMP_DOWNLOAD_DIR/$distriutionUrlName" -C "$TMP_DOWNLOAD_DIR" || die "failed to untar"
fi
printf %s\\n "$distriutionUrl" >"$TMP_DOWNLOAD_DIR/$distriutionUrlNameMain/mvnw.url"
mv -- "$TMP_DOWNLOAD_DIR/$distriutionUrlNameMain" "$MAVEN_HOME" || [ -d "$MAVEN_HOME" ] || die "fail to move MAVEN_HOME"

clean || :
exec_maven "$@"
