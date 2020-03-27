jKeyring: The Netbeans&trade; keyring, without Netbeans
=============

jKeyring is an abstraction for secure storage of credential information that is implemented on top of Windows DPAPI, MacOS X Keyring, KDE KWallet or GNOME Keyring, with a default implementation when none of the underlying native keyrings are available. It was originally written as part of Netbeans. We excised the core functionality from Netbeans and created this independent library as part of the jOVAL&trade; project.

## Changes

Modifications by aanno:

* new codebase based on apache netbeans commit 3256a04696ce5 (post 12.0-beta1)
* added gradle build script
* goal: minimal code changes i.r.t. netbeans, hence
  + fake netbean special stuff, i.e. provide a minimal working implementation
  + use classindex for lookup provider
  + use jsr305 instead of nb specifics
  + use junit5 (and mockito) instead of nb specifics
  + now swing/awt (if possible at all)
  
At present, the old implementation is still there for reference.
