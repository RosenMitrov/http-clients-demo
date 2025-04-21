# Trigger a reload (soft refresh) manually:

Use the WireMock Admin API to reload mappings without restarting the container:
- curl -X POST http://localhost:9999/__admin/mappings/reset

This will load new or updated mappings from your mounted mappings/ directory.
- curl -X POST http://localhost:9999/__admin/mappings/reload
