# The Java devcontainer
FROM mcr.microsoft.com/devcontainers/java:0-17

USER root

# Install X11
RUN apt update && \
  apt install -y --no-install-recommends \
  libgl1-mesa-glx
